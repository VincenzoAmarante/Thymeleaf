package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    
    private final AuthorRepository autoRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository autoRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.autoRepository = autoRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("started in bootstrap");

        Publisher publisher=new Publisher();
        publisher.setName("SFG publishing");
        publisher.setName("St peesbourg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher count "+publisherRepository.count() );

        Author eric=new Author("Eric", "Evans");
        Book  ddd=new Book("Domain Driven Desing","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        autoRepository.save(eric);
        bookRepository.save(ddd);

        Author rod=new Author("Rod", "Johnson");
        Book  noEJB=new Book("J2EE Development no EJB","321321");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        autoRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("number of books "+bookRepository.count() );
        System.out.println("publisher number of books "+publisher.getBooks().size() );
    }
}
