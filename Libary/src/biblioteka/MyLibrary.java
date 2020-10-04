package biblioteka;

import java.util.*;
import java.util.stream.Collectors;

import com.eisgroup.javaexam.library.Book;
import com.eisgroup.javaexam.library.Library;

public class MyLibrary implements Library {
	Collection<Book> mybooks = new ArrayList<Book>();

	@Override
	public Collection<Book> findByAuthor(String author) {

		return mybooks.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
	}

	@Override
	public Book findByTitle(String title) {
		return this.mybooks.stream().findFirst().orElse(null);
	}

	@Override
	public Collection<Book> getBooksSortedByPageCount() {
		List<Book> sb = new ArrayList<>(mybooks);
		sb.sort((s1, s2) -> s1.getPageCount() - s2.getPageCount());
		return sb;
	}

	@Override
	public int getNumberOfBooks() {
		return mybooks.size();
	}

	@Override
	public void takeABook(Book arg0) {
		if (!mybooks.contains(arg0)) {
			mybooks.add(arg0);
		}
	}
}
