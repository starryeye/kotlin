package dev.starryeye.book_library.application.book.query

import dev.starryeye.book_library.application.book.query.result.CountBookByCategoryResult
import dev.starryeye.book_library.application.book.query.result.CountBooksByCategoryResult
import dev.starryeye.book_library.domain.book.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CountBooksByCategoryService(
    private val bookRepository: BookRepository,
) {

    fun countBooksByCategory(): CountBooksByCategoryResult {
        return CountBooksByCategoryResult(
            stats = bookRepository.countGroupByCategory()
                .map { projection ->
                    CountBookByCategoryResult(
                        category = projection.category,
                        count = projection.count.toInt()
                    )
                }
        )

        // projection 안쓰고 application memory 에서 계산
//        return CountBooksByCategoryResult(bookRepository.findAll() // List<Book>
//            .groupBy { book -> book.category } // Map<BookCategory, List<Book>>
//            .map { (category, books) -> CountBookByCategoryResult(category, books.size) } // List<CountBookByCategoryResult>
//        )
    }
}
