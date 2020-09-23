TODO 
- disable button (e.g. first page, last page)
- Sort by fields 
- Authentication/Authorization 
- Add other fields: gender 
- Language selection (set locale)

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