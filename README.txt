== Running the App ==

This project is configured with an embedded Jetty instance. Jetty can be
launched from maven with the following command:

mvn jetty:run

Ctrl-C to stop the maven instance.

Note: As we are using Maven, you will need to be connected to the internet to download
the dependencies.

This project also uses HSQLDB for the database.  The database is automatically stored in
the target directory.  Running an 'mvn clean' should remove any data you have created through
the application.

== Tasks ==

=== Beginner ===
* Add logging to:
** DefaultPostService
** NewLinkPostController
** NewQuotePostController
** NewTextPostController
* The "Home" Link in the template does not redirect to the home page.
* Add unit tests for the Sping WebMVC controllers.

=== Intermediate ===
* Add JMX metrics and notifications to all DAOs
* Change the Post to abstract.  Yes this means the database structure will change.
* Include some notes about how data would need to be migrated to account for 
  the preceeding change.  Javadocs in the DAO are fine.
* Add an error page

=== Advanced ===
* Add REST service for:
** Fetching more posts
** Creating a post
* Add Transaction Support through Annotations
*: Don't forget to remove the OpenSessionInViewFilter

=== Super Awesome Advanced ===
* On the home page, only load a few posts and have a DWR backed "Load More" button that will
  return more results.

== Submitting your App ==

# Archive your project using
#: mvn clean package
# Email the target/stumblr-1.0-SNAPSHOT-project.zip to hr@acistek.com
