package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class AdvanceType {
    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * GUID : 8A3365CE94874A16963FF552534B8235
         * ID : 1041
         * CodeType : InformType
         * CodeTypeName : 举报类型
         * CodeName : 付款后未发货
         * Code : 1
         * IsDelete : 1
         */

        private String GUID;
        private int ID;
        private String CodeType;
        private String CodeTypeName;
        private String CodeName;
        private String Code;
        private int IsDelete;

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCodeType() {
            return CodeType;
        }

        public void setCodeType(String CodeType) {
            this.CodeType = CodeType;
        }

        public String getCodeTypeName() {
            return CodeTypeName;
        }

        public void setCodeTypeName(String CodeTypeName) {
            this.CodeTypeName = CodeTypeName;
        }

        public String getCodeName() {
            return CodeName;
        }

        public void setCodeName(String CodeName) {
            this.CodeName = CodeName;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
            this.IsDelete = IsDelete;
        }
    }
}
