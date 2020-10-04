package biblioteka;

import com.eisgroup.javaexam.library.Library;
import com.eisgroup.javaexam.library.test.BaseLibraryTest;

public class MyBaseLibaryTest extends BaseLibraryTest {

	@Override
	protected Library getLibrary() {
		MyLibrary librarys = new MyLibrary();
		return librarys;
	}

	

}
