package huuphu.aprotrain.client_app.Model;

import java.sql.Date;
import java.util.ArrayList;

    public class ListOrder{
        public ArrayList<OrderItem> content;
        public Pageable pageable;
        public int totalPages;
        public int totalElements;
        public boolean last;
        public Sort sort;
        public int size;
        public int number;
        public int numberOfElements;
        public boolean first;
        public boolean empty;

        public ArrayList<OrderItem> getContent() {
            return content;
        }

        public void setContent(ArrayList<OrderItem> content) {
            this.content = content;
        }

        public Pageable getPageable() {
            return pageable;
        }

        public void setPageable(Pageable pageable) {
            this.pageable = pageable;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public Sort getSort() {
            return sort;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public ListOrder() {
        }

        public ListOrder(ArrayList<OrderItem> content, Pageable pageable, int totalPages, int totalElements, boolean last, Sort sort, int size, int number, int numberOfElements, boolean first, boolean empty) {
            this.content = content;
            this.pageable = pageable;
            this.totalPages = totalPages;
            this.totalElements = totalElements;
            this.last = last;
            this.sort = sort;
            this.size = size;
            this.number = number;
            this.numberOfElements = numberOfElements;
            this.first = first;
            this.empty = empty;
        }
    }


