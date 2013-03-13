# ulysses

[![Build Status](https://secure.travis-ci.org/coreyjonoliver/ulysses.png?branch=master)](http://travis-ci.org/coreyjonoliver/ulysses)

_ulysses_ is an INI parser implemented using Scala's parser combinators.

### Usage

_ulysses_ is simple to use.

Start by providing the appropriate imports: 

    import org.coreyoliver.ulysses.ini._

Feel free to parse away:

Parse an INI string (or file) into an Ini object

        val source = """
        [Section1]
        key1=value1
        """
        val ini = Ini.loadString(source)

Pretty print a Ini object to produce a String

    val iniStr = ini.prettyPrint

An Ini object is simply a case class with a sections field. For example, to retrieve a section with the name Section1, you would provide: 

    val section1 = ini.getSection("Section1")
        
You can then determine the value of a key in the section:

    val value1 = section1.getValue("key1")

Methods which perform parsing return a ValidationNEL[String, String] type from the scalaz library. In the case of a parsing failure, a Failure("reason for failure") will be returned. The positive case will yield a Success containing an Ini object.

### License

_ulysses_ is licensed under [APL 2.0].
