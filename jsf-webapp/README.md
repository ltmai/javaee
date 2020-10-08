TODO:
- Mark active language

CRUD demo in JSF

# General description

This project demonstrates CRUD operations on Customers instances. 

# Create operation (create.xhtml)

This view creates a new Customer and stores in database.
View parameter: none

# Retrieve operation (index.xhtml)

This view lists the Customers in database. It supports paging navigation 
by maintaining a current page number. 

View parameter: page (http://localhost:8080/jsf-webapp/index.xhtml?page=1)

The parmeter is set to bean property as follows:
```xhtml
<f:metadata>
    <f:viewParam name="page" value="#{customerController.page}" />
</f:metadata>
```

# Update operation (edit.xhtml)

This view shows an existing Customer for edit and stores the changes back
to database on submit. 

View parameter: id (the customer to be edited)

```xhtml
<f:metadata>
    <f:viewParam name="id" value="#{customerController.id}" />
    <f:viewAction action="#{customerController.findCustomerById()}"/>
</f:metadata>
```

# Delete operation (remove.xhtml)

This view asks the user to confirm removal of a Customer.

View parameter: id (of the customer to be removed)

```xhtml
<f:metadata>
    <f:viewParam name="id" value="#{customerController.id}" />
    <f:viewAction action="#{customerController.findCustomerById()}"/>
</f:metadata>
```

# Convert message resources from UTF-8 to ASCII


```
native2ascii -encoding UTF-8 src\main\resources\com\example\webapp\i18n\messages_cn.utf8.properties src\main\resources\com\example\webapp\i18n\messages_cn.properties
```

```
native2ascii - Native-to-ASCII Converter
Converts a file with characters in any supported character encoding to one with ASCII and/or Unicode escapes, or visa versa.
SYNOPSIS
native2ascii [options] [inputfile [outputfile]]
DESCRIPTION
native2ascii converts files that are encoded to any character encoding that is supported by the Java runtime environment to files encoded in ASCII, using Unicode escapes ("\uxxxx" notation) for all characters that are not part of the ASCII character set. This process is required for properties files containing characters not in ISO-8859-1 character sets. The tool can also perform the reverse conversion.
If outputfile is omitted, standard output is used for output. If, in addition, inputfile is omitted, standard input is used for input.

OPTIONS
-reverse
Perform the reverse operation: Convert a file encoded in ISO-8859-1 with Unicode escapes to a file in any character encoding supported by the Java runtime environment.

-encoding encoding_name
Specifies the name of the character encoding to be used by the conversion procedure. If this option is not present, the default character encoding (as determined by the java.nio.charset.Charset.defaultCharset method) is used. The encoding_name string must be the name of a character encoding that is supported by the Java runtime environment - see the Supported Encodings document.

-Joption
Pass option to the Java virtual machine, where option is one of the options described on the reference page for the java application launcher. For example, -J-Xms48m sets the startup memory to 48 megabytes.
```