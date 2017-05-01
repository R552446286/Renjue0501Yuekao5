package com.baway.yuekao5.bean;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/5/1 17:12
 */
public class HouseBean {
    public Result result;

    @Override
    public String toString() {
        return "HouseBean{" +
                "result=" + result +
                '}';
    }
    public class Result{
        public List<Rows> rows;

        @Override
        public String toString() {
            return "Result{" +
                    "rows=" + rows +
                    '}';
        }
        public class Rows{
            public Info info;

            @Override
            public String toString() {
                return "Rows{" +
                        "info=" + info +
                        '}';
            }
            public class Info{
                public String default_image;
                public String loupan_name;
                public String new_price_back;
                public String new_price_value;
                public Recommend_price recommend_price;
                public String region_title;
                public String sale_title;
                public String sub_region_title;
                public String tags;

                @Override
                public String toString() {
                    return "Info{" +
                            "default_image='" + default_image + '\'' +
                            ", loupan_name='" + loupan_name + '\'' +
                            ", new_price_back='" + new_price_back + '\'' +
                            ", new_price_value='" + new_price_value + '\'' +
                            ", recommend_price=" + recommend_price +
                            ", region_title='" + region_title + '\'' +
                            ", sale_title='" + sale_title + '\'' +
                            ", sub_region_title='" + sub_region_title + '\'' +
                            ", tags='" + tags + '\'' +
                            '}';
                }

                public class Recommend_price{
                    public String back;
                    public int value;

                    @Override
                    public String toString() {
                        return "Recommend_price{" +
                                "back='" + back + '\'' +
                                ", value=" + value +
                                '}';
                    }
                }
            }
        }
    }
}
