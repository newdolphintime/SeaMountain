package com.play.zv.seamountain.api.AvjsoupApi;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hspc on 2017/5/18.
 */

public class GetJavbus {
    static String BASEURL = "https://www.javbus.com/";

    /**
     * 根据番号获取信息
     *
     * @param num
     * @return
     * @throws Exception
     */
    public static MovieInfo getInfo(String num) throws Exception {

        num = num.toUpperCase();
        MovieInfo movie = new MovieInfo();

        Document doc;
        try {
            doc = Jsoup.connect(BASEURL + num).get();

            String title = doc.select("h3").text();
            // 封面
            Elements mainimg = doc.select(".bigImage");
            movie.setCover(mainimg.first().attr("href"));

            // 截图
            Elements Screenshot = doc.select(".sample-box");
            List<String> previews = new ArrayList<String>();
            for (Element element : Screenshot) {
                previews.add(element.attr("href"));
            }
            movie.setPreviews(previews);

            // 有没有码
            Elements classificationElements = doc.select("li[class=active]");
            String classification = classificationElements.text();
            movie.setCensored(classification);

            // 详细信息
            Elements info = doc.select(".info");
            Elements infos = info.select("p");
            for (int i = 0; i < infos.size(); i++) {
                String header = infos.get(i).select(".header").text();
                if ("識別碼:".equals(header)) {
                    movie.setNum(infos.get(i).text().replaceAll(header + " ", ""));
                    title = title.replaceAll(movie.getNum(), "");
                } else if ("發行日期:".equals(header)) {
                    movie.setRelease(infos.get(i).text().replaceAll(header + " ", ""));
                } else if ("長度:".equals(header)) {
                    movie.setRunTime(infos.get(i).text().replaceAll(header + " ", ""));
                } else if ("導演:".equals(header)) {
                    movie.setDirector(infos.get(i).text().replaceAll(header + " ", ""));
                } else if ("製作商:".equals(header)) {
                    movie.setStudio(infos.get(i).text().replaceAll(header + " ", ""));
                } else if ("發行商:".equals(header)) {
                    movie.setLabel(infos.get(i).text().replaceAll(header + " ", ""));
                } else if ("系列:".equals(header)) {
                    movie.setSeries(infos.get(i).text().replaceAll(header + " ", ""));
                } else if ("類別:".equals(header) || "演員".equals(header)) {
                    // System.err.println("特殊字段");
                } else {
                    // System.err.println(header+"未识别的字段");
                }
            }

            // 类别
            List<String> genres = new ArrayList<String>();
            Elements select = infos.select(".genre");
            for (Element element : select) {
                if (!element.html().contains("star")) {
                    genres.add(element.text());
                }
            }
            movie.setGenres(genres);

            // 演员
            List<Star> stars = new ArrayList<>();
            Elements star = infos.select("span[onmouseover]");

            for (Element element : star) {
                stars.add(getStarInfo(element.select("a").attr("href")));
                title = title.replaceAll(element.text(), "");
            }

            movie.setStars(stars);
            movie.setTitle(title.trim());

            //磁链信息
            List<Magnet> magnets = getMagnets(doc, num);
            movie.setMagnet(magnets);
            return movie;
        } catch (HttpStatusException e) {
            System.out.println(num + "不存在");
        }
        return null;
    }

    /**
     * 解析<script>标签获取var的参数
     *
     * @param e
     * @return
     */
    public static Map<String, String> getJsParams(Elements e) {
        /* 用來封裝要保存的参数 */
        Map<String, String> map = new HashMap<String, String>();
        for (Element element : e) {

			/* 取得JS变量数组 */
            String[] data = element.data().toString().split("var");

			/* 取得单个JS变量 */
            for (String variable : data) {

				/* 过滤variable为空的数据 */
                if (variable.contains("=")) {

					/* 取到满足条件的JS变量 */
                    if (variable.contains("gid") || variable.contains("uc") || variable.contains("color")
                            || variable.contains("img")) {

                        String[] kvp = variable.split("=");

						/* 取得JS变量存入map */
                        if (!map.containsKey(kvp[0].trim()))
                            map.put(kvp[0].trim(), kvp[1].trim().substring(0, kvp[1].trim().length() - 1).toString());
                    }
                }
            }
        }
        map.put("lang", "zh");
        return map;
    }

    /**
     * 解析磁力链接
     *
     * @param doc
     * @return
     * @throws IOException
     */
    public static List<Magnet> getMagnets(Document doc, String num) throws IOException {
        List<Magnet> magnetList = new ArrayList<>();

        Elements e = doc.getElementsByTag("script").eq(8);
        Map<String, String> jsParams = getJsParams(e);
        String url = "https://www.javbus.com/ajax/uncledatoolsbyajax.php";
        Connection con = Jsoup.connect(url);
        con.data(jsParams);
        con.header("referer", BASEURL + num);
        Document document = con.get();
        // 磁力链接
        Elements magnets = document.select("a");

        Magnet magnet = new Magnet();
        boolean skip = false;
        int index = 0;

        for (int i = 0; i < magnets.size(); i++) {
            if (!"高清".equals(magnets.get(i).text())) {
                if (!"字幕".equals(magnets.get(i).text())) {
                    switch (index) {
                        case 0:
                            magnet.setMagnetTitle(magnets.get(i).text());
                            index++;
                            break;
                        case 1:
                            magnet.setMagnetSize(magnets.get(i).text());
                            index++;
                            break;
                        case 2:
                            magnet.setMagnetData(magnets.get(i).text());

                            index = 0;
                            //补全缺少的值
                            magnet.setMagnetNum(num);
                            String magnetsUrl = magnets.get(i).attr("href");
                            magnet.setMagnetUrl(magnetsUrl.substring(0, (magnetsUrl.lastIndexOf("&") == -1) ? magnetsUrl.length() : magnetsUrl.lastIndexOf("&")));
                            //存起来
                            magnetList.add(magnet);

                            //重置magnet对象
                            magnet = new Magnet();
                            break;
                        default:
                            System.out.println(index);
                            break;
                    }
                }
            } else {
                magnet.setIsHD(true);
            }
        }
        return magnetList;
    }

    /**
     * 获取演员信息...
     *
     * @param StarUrl
     * @return
     * @throws IOException
     */
    public static Star getStarInfo(String StarUrl) throws IOException {
        Star star = new Star();

        Document document = Jsoup.connect(StarUrl).get();

        Elements img = document.select(".avatar-box > .photo-frame img");

        star.setImage(img.attr("src"));

        Elements select = document.select(".avatar-box .photo-info > *");
        for (Element element : select) {
            if ("span".equals(element.tagName())) {
                star.setName(element.text());
            } else {
                String[] split = element.text().split(": ");
                switch (split[0]) {
                    case "生日":
                        star.setBirthday(split[1]);
                        break;
                    case "年齡":
                        star.setAge(split[1]);
                        break;
                    case "身高":
                        star.setHeight(split[1]);
                        break;
                    case "罩杯":
                        star.setCup(split[1]);
                        break;
                    case "胸圍":
                        star.setBust(split[1]);
                        break;
                    case "腰圍":
                        star.setWaist(split[1]);
                        break;
                    case "臀圍":
                        star.setHips(split[1]);
                        break;
                    case "出生地":
                        star.setHometown(split[1]);
                        break;
                    case "愛好":
                        star.setHometown(split[1]);
                        break;
                    default:
                        System.err.println(StarUrl + ":");
                        System.err.println("意料之外的参数:" + element.text());
                        break;
                }
            }
        }
        return star;
    }
}
