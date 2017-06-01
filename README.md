Camel Batch Job
===============

A demo project on how to use camel to run a batch, in this case transforming some XML with "book data" from folder
"books" to database entities for "pages".

Camel is just too great in building transformation pipelines and connecting painlessly to data sources/sinks to ignore
it when you need to do that pesky data migration job, but normally it wants to run as a server and poll the input
files/database while you only want it to run once on the input and then die.

This project outlines how to disable that behaviour by using a special PollingConsumerPollStrategy in the form of
de.consol.ow.mypages.RunOncePollingConsumer class, which lets Camel only poll once and shuts down the service on the
second poll.

Start the batch job by: mvn clean package exec:java.

To re-run after one import simply move the demo XML from books/.camel/book1.xml (where Camel puts it) back to
books/book.1xml.

 Other facts on the project:

 - This uses CDI to wire up Camel with its Routes and Beans, as I'm used to that from regular JEE development, but this
   is no neccessity for the batch thing
 - It also uses JAXB for XML unmarshalling and JPA for database output to an H2 db. Again: irrelevant when you are only
   interested in the batch, but hey, it's an actually running demo!
 - The database being put out to is a H2 db in folder "db"