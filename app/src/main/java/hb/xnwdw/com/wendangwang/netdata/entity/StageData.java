package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StageData {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * GUID : 6e58dad35fcf4c08a27146763d9f1774
         * LowLimit : 0.0
         * Freight : 0.0
         * ID : 120
         * Provence : 北京市|天津市|上海市|江西省|西藏自治区|湖南省|台湾省|内蒙古自治区|广西壮族自治区|澳门特别行政区|甘肃省
         */

        private String GUID;
        private double LowLimit;
        private int Freight;
        private int ID;
        private String Provence;

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public double getLowLimit() {
            return LowLimit;
        }

        public void setLowLimit(double LowLimit) {
            this.LowLimit = LowLimit;
        }

        public int getFreight() {
            return Freight;
        }

        public void setFreight(int Freight) {
            this.Freight = Freight;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getProvence() {
            return Provence;
        }

        public void setProvence(String Provence) {
            this.Provence = Provence;
        }
    }
}
