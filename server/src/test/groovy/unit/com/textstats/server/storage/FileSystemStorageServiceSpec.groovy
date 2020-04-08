package unit.com.textstats.server.storage

import com.textstats.server.storage.FileSystemStorageService
import com.textstats.server.storage.StorageException
import com.textstats.server.storage.StorageProperties
import org.springframework.mock.web.MockMultipartFile
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


@Subject(FileSystemStorageService)
class FileSystemStorageServiceSpec extends Specification{
    @Shared
    FileSystemStorageService fileSystemStorageService

    def setupSpec() {
        StorageProperties storageProperties = Mock(StorageProperties)
        storageProperties.location >> "test"
        fileSystemStorageService = new FileSystemStorageService(storageProperties)
    }


    def "Should get an error when using invalid/violating file names"() {
        when: "file name contains security vulnerabity "

        def file = new MockMultipartFile("test..name.txt", "test..name.txt", "text/plain", "test data".getBytes());

        fileSystemStorageService.storeFile(file)
        then:"Error is thrown"
        final StorageException storageException = thrown()
        storageException.message != null
        storageException.message.contains("Cannot store file with relative path outside current directory ")
    }

}
