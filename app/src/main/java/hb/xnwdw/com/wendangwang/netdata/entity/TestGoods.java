package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

import hb.xnwdw.com.wendangwang.gui.widget.PagerDotIndicator;

/**
 * Created by Administrator on 2017/3/10.
 */

public class TestGoods  {


    private int imgId;
    private String goodsName;
    private String goodsNum;
    private String goodsPrice;
    private String goodsBiaoqian;

    private int imgId2;
    private String goodsName2;
    private String goodsNum2;
    private String goodsPrice2;
    private String goodsBiaoqian2;


    public TestGoods(int imgId, String goodsName, String goodsNum, String goodsPrice, String goodsBiaoqian, int imgId2, String goodsName2, String goodsNum2, String goodsPrice2, String goodsBiaoqian2) {
        this.imgId = imgId;
        this.goodsName = goodsName;
        this.goodsNum = goodsNum;
        this.goodsPrice = goodsPrice;
        this.goodsBiaoqian = goodsBiaoqian;
        this.imgId2 = imgId2;
        this.goodsName2 = goodsName2;
        this.goodsNum2 = goodsNum2;
        this.goodsPrice2 = goodsPrice2;
        this.goodsBiaoqian2 = goodsBiaoqian2;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsBiaoqian() {
        return goodsBiaoqian;
    }

    public void setGoodsBiaoqian(String goodsBiaoqian) {
        this.goodsBiaoqian = goodsBiaoqian;
    }

    public int getImgId2() {
        return imgId2;
    }

    public void setImgId2(int imgId2) {
        this.imgId2 = imgId2;
    }

    public String getGoodsName2() {
        return goodsName2;
    }

    public void setGoodsName2(String goodsName2) {
        this.goodsName2 = goodsName2;
    }

    public String getGoodsNum2() {
        return goodsNum2;
    }

    public void setGoodsNum2(String goodsNum2) {
        this.goodsNum2 = goodsNum2;
    }

    public String getGoodsPrice2() {
        return goodsPrice2;
    }

    public void setGoodsPrice2(String goodsPrice2) {
        this.goodsPrice2 = goodsPrice2;
    }

    public String getGoodsBiaoqian2() {
        return goodsBiaoqian2;
    }

    public void setGoodsBiaoqian2(String goodsBiaoqian2) {
        this.goodsBiaoqian2 = goodsBiaoqian2;
    }
}
