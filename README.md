java8-playground
================
Quick presentation of new Java 8 features.
As a base of presentation I have proposed piece of generic game business logic. 
Please do not pay attention to the architecture of the code, this is a proposal to illustrate examples of using the new Java 8 syntax.

For readability every method is presented in two manners: "classic" with callback methods (should be friendly for Java 6/7 Guava users), and the other more java 8 way, with lambda notations.
Every method has its own unit test which should be descriptive enough.

I am aware that some of tests could be more condensed using additional libraries (like Hamcrest), however, for the simplicity sake I decided not to rely on additional codebase.
TODO
----
Plan for near future: parallel streams


References
----------
* [default methods](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html) (oracle docs)
* [optional](http://docs.oracle.com/javase/8/docs/api/java/util/Optional.html) (oracle docs)
* [reduction](http://docs.oracle.com/javase/tutorial/collections/streams/reduction.html) (oracle docs)


![Travis CI](https://travis-ci.org/milpol/java8-playground.svg)
