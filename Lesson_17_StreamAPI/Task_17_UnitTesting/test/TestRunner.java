import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({MainTest.class, SimpleMathLibraryTest.class})
public class TestRunner {
}
