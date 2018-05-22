package parser;

import br.ufal.ic.parser.ParsingTable;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingTableTest {

    @Test
    void getParsingTable(){
        Hashtable<Pair<String, String>, List<String>> parsingTable = ParsingTable.getParsingTable();

        assertEquals(362, parsingTable.size());
    }

}
