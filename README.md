# ulysses

[![Build Status](https://secure.travis-ci.org/coreyjonoliver/ulysses.png?branch=master)](http://travis-ci.org/coreyjonoliver/ulysses)

_ulysses_ is an INI parser implemented using Scala's parser combinators.

### Usage

_ulysses_ is simple to use.

Start by providing the appropriate imports: 

    import org.coreyoliver.ulysses._

Feel free to parse away:

1. Parse an INI string (or file) into an Ini object

        val source = """
        [Section1]
        key1=value1
        """
        val ini = source.asIni

2. Pretty print a Ini object to produce a String

    val iniStr = ini.prettyPrint

An Ini object is simply a case class with a sections field. For example, to retrieve a section with the name Section1, you would provide: 

    val section1 = ini.getSection("Section1")
        
You can then determine the value of a key in the section:

    val value1 = section1.getValue("key1")
  
### License

_ulysses_ is licensed under [APL 2.0].
