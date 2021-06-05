package com.haha.fastproject.base.contract;


import java.util.List;

/**
 * @author zhe.chen
 * @date 2019-11-20 15:46
 * Des:
 */
public class GiftBean extends BaseResponse {

    /**
     * stat : 200
     * version : 13
     * gifts : {"1":{"url":"https://img.sevenkm.cn/gift/1.png/160","id":1,"name":"玫瑰","gid":1,"price":318,"type":3,"num":9,"svgaurl":"https://img.sevenkm.cn/gift/meigui.svga"},"2":{"url":"https://img.sevenkm.cn/gift/2.png/160","id":2,"name":"520","gid":1,"price":520,"type":3,"num":9,"svgaurl":"https://img.sevenkm.cn/gift/love.svga"},"3":{"url":"https://img.sevenkm.cn/gift/3.png/160","id":3,"name":"普天同庆","gid":1,"price":88,"type":3,"num":9,"svgaurl":"https://img.sevenkm.cn/gift/yanhua.svga"},"4":{"url":"https://img.sevenkm.cn/gift/4.png/160","id":4,"name":"一生一世","gid":1,"price":1314,"type":3,"num":9,"svgaurl":"https://img.sevenkm.cn/gift/yishengyishi.svga"},"5":{"url":"https://img.sevenkm.cn/gift/5.png/160","id":5,"name":"666","gid":1,"price":88,"type":3,"num":9,"svgaurl":"https://img.sevenkm.cn/gift/666.svga"},"6":{"url":"https://img.sevenkm.cn/gift/6.png/160","id":6,"name":"钻戒","gid":1,"price":520,"type":3,"num":8,"svgaurl":"https://img.sevenkm.cn/gift/zuanjie.svga"},"7":{"url":"https://img.sevenkm.cn/gift/7.png/160","id":7,"name":"城堡","gid":1,"price":5200,"type":3,"num":8,"svgaurl":"https://img.sevenkm.cn/gift/chengbao.svga"},"8":{"url":"https://img.sevenkm.cn/gift/8.png/160","id":8,"name":"海滩","gid":1,"price":2113,"type":3,"num":8,"svgaurl":"https://img.sevenkm.cn/gift/haitan.svga"}}
     */
    private int version = 0;
    private List<GiftsBean> gifts;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<GiftsBean> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftsBean> gifts) {
        this.gifts = gifts;
    }

    public static class GiftsBean {
        /**
         * url : https://img.sevenkm.cn/gift/1.png/160
         * id : 1
         * name : 玫瑰
         * gid : 1
         * price : 318
         * type : 3
         * num : 9
         * svgaurl : https://img.sevenkm.cn/gift/meigui.svga
         */

        private String url;
        private int id = -1;
        private String name;
        private int gid = -1;
        private int price = -1;
        private int type = -1;
        private int num = -1;
        private String svgaurl;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getSvgaurl() {
            return svgaurl;
        }

        public void setSvgaurl(String svgaurl) {
            this.svgaurl = svgaurl;
        }

        @Override
        public String toString() {
            return "GiftsBean{" +
                    "url='" + url + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", gid=" + gid +
                    ", price=" + price +
                    ", type=" + type +
                    ", num=" + num +
                    ", svgaurl='" + svgaurl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GiftBean{" +
                "version=" + version +
                ", gifts=" + gifts +
                '}';
    }
}
