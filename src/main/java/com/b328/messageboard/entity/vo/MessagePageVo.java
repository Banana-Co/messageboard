package com.b328.messageboard.entity.vo;

public class MessagePageVo {
    /**
     * 页面数
     */
        private int pageNum;

    /**
     * 页面大小
     */
        private int pageSize;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
}
