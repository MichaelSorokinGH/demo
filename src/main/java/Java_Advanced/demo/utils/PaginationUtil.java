package Java_Advanced.demo.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtil {
    public static Pageable getPageRequest(Integer page, Integer perPage, String sort, Sort.Direction order) {
//        Page - номер страницы, perPage - количество элементов на странице,
//                Sort - по какому полю мы будем сортировать,
//                order - либо мы в обратном порядке отдаём либо в том в котором сортировали
        if (page == null) {
            page = 0;
        } else if (page > 0) {
            page = page - 1;
        }
//Нумерация начинается с нуля, могут передать нумерацию, которая начинается с 1, тогда ставим page минус один

        if (perPage == null) {
            perPage = 10;
        }
//Количество элементов, если их не передали будет фиксированное количество - 10
        if (order == null || sort == null) {
            return PageRequest.of(page, perPage);
        } else if (order.equals("DESC")) {
            return PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort));
        } else {
            return PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort));
        }
    }
}
