package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/8/22.
 */

public class WhatIsShow {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"seckillDisplay":1,"newGoodsDisplay":0,"IndexBrandDispaly":0,"GoodsDetailBrandDispaly":0}
     * dataStatus : 1
     * describe : null
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private ObjBean obj;
    private int dataStatus;
    private Object describe;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSumSize() {
        return sumSize;
    }

    public void setSumSize(int sumSize) {
        this.sumSize = sumSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Object getDescribe() {
        return describe;
    }

    public void setDescribe(Object describe) {
        this.describe = describe;
    }

    public static class ObjBean {
        /**
         * seckillDisplay : 1
         * newGoodsDisplay : 0
         * IndexBrandDispaly : 0
         * GoodsDetailBrandDispaly : 0
         */

        private int seckillDisplay;
        private int newGoodsDisplay;
        private int IndexBrandDispaly;
        private int GoodsDetailBrandDispaly;

        public int getSeckillDisplay() {
            return seckillDisplay;
        }

        public void setSeckillDisplay(int seckillDisplay) {
            this.seckillDisplay = seckillDisplay;
        }

        public int getNewGoodsDisplay() {
            return newGoodsDisplay;
        }

        public void setNewGoodsDisplay(int newGoodsDisplay) {
            this.newGoodsDisplay = newGoodsDisplay;
        }

        public int getIndexBrandDispaly() {
            return IndexBrandDispaly;
        }

        public void setIndexBrandDispaly(int IndexBrandDispaly) {
            this.IndexBrandDispaly = IndexBrandDispaly;
        }

        public int getGoodsDetailBrandDispaly() {
            return GoodsDetailBrandDispaly;
        }

        public void setGoodsDetailBrandDispaly(int GoodsDetailBrandDispaly) {
            this.GoodsDetailBrandDispaly = GoodsDetailBrandDispaly;
        }
    }
}
