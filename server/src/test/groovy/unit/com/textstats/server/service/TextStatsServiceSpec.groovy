package unit.com.textstats.server.service

import com.textstats.server.model.TextStats
import com.textstats.server.service.TextStatsService
import com.textstats.server.service.TextStatsServiceImpl
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.nio.file.Path

@Subject(TextStatsServiceImpl)
class TextStatsServiceSpec extends Specification{

    @Shared
    TextStatsService textStatsService;

    def setupSpec() {
        textStatsService = new TextStatsServiceImpl()
    }


    def " Text Stats Service returns the correct statistics"() {
        given: "a sample text file"
        File file = new File(this.getClass().classLoader.getResource("Sample.txt").toURI());
        Path path = Path.of(file.toURI())
        when: "text stats getWordCountStats API is involed"
        TextStats stats = textStatsService.getStats(path,3)
        then: "correct stats are returned"
        stats != null
        // validate word count stats
        stats.wordCount > 20
        stats.frequentlyUsedWords != null
        stats.frequentlyUsedWords.contains("sample")

        stats.characterCount > 50
        stats.frequentlyUsedCharacters.contains("s")

        stats.symbolCount > 0
        stats.frequentlyUsedSymbols.contains("!")
    }
}
