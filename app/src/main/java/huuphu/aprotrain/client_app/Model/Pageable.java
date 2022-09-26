package huuphu.aprotrain.client_app.Model;

public class Pageable {
        Sort SortObject;
        private float pageSize;
        private float pageNumber;
        private float offset;
        private boolean unpaged;
        private boolean paged;

    public Pageable(Sort sortObject, float pageSize, float pageNumber, float offset, boolean unpaged, boolean paged) {
        SortObject = sortObject;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.offset = offset;
        this.unpaged = unpaged;
        this.paged = paged;
    }

    public Pageable() {
    }

    public Sort getSortObject() {
        return SortObject;
    }

    public void setSortObject(Sort sortObject) {
        SortObject = sortObject;
    }

    public boolean isUnpaged() {
        return unpaged;
    }

    public boolean isPaged() {
        return paged;
    }

    // Getter Methods

        public Sort getSort() {
            return SortObject;
        }

        public float getPageSize() {
            return pageSize;
        }

        public float getPageNumber() {
            return pageNumber;
        }

        public float getOffset() {
            return offset;
        }

        public boolean getUnpaged() {
            return unpaged;
        }

        public boolean getPaged() {
            return paged;
        }

        // Setter Methods

        public void setSort(Sort sortObject) {
            this.SortObject = sortObject;
        }

        public void setPageSize(float pageSize) {
            this.pageSize = pageSize;
        }

        public void setPageNumber(float pageNumber) {
            this.pageNumber = pageNumber;
        }

        public void setOffset(float offset) {
            this.offset = offset;
        }

        public void setUnpaged(boolean unpaged) {
            this.unpaged = unpaged;
        }

        public void setPaged(boolean paged) {
            this.paged = paged;
        }
    }
