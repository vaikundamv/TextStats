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


    def "GetWordCount API returns the correct count"() {
        given: "a sample text file"
        File file = new File(this.getClass().classLoader.getResource("Sample.txt").toURI());
        Path path = Path.of(file.toURI())
        when: "text stats getWordCountStats API is involed"
        TextStats stats = textStatsService.getWordCountStats(path)
        then: "correct word count is returned"
        stats != null
        stats.wordCount == 25
    }
}
