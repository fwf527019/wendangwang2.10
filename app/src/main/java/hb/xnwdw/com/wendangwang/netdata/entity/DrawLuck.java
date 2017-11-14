package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/7/2.
 */

public class DrawLuck {


    /**
     * dataSatatus : 1
     * obj : {"iGrade":1}
     */

    private int dataSatatus;
    private ObjBean obj;

    public int getDataSatatus() {
        return dataSatatus;
    }

    public void setDataSatatus(int dataSatatus) {
        this.dataSatatus = dataSatatus;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * iGrade : 1
         */

        private int iGrade;

        public int getIGrade() {
            return iGrade;
        }

        public void setIGrade(int iGrade) {
            this.iGrade = iGrade;
        }
    }
}
