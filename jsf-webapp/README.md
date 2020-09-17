TODO 
- Using template in create/edit/remove/detail.xhtml
- reuse form: 
    https://stackoverflow.com/questions/4792862/how-to-include-another-xhtml-in-xhtml-using-jsf-2-0-facelets
    https://stackoverflow.com/questions/8203739/reusing-a-form-in-two-different-views-jsf
- Sort by fields 
- Authentication/Authorization 
- Add other fields: gender 
- Internationalization

CRUD demo in JSF

# General description

This project demonstrates CRUD operations on Customers instances. 

# Create operation (create.xhtml)

This view creates a new Customer and stores in database.
View parameter: none

# Retrieve operation (index.xhtml)

This view lists the Customers in database. It supports paging navigation 
by maintaining a current page number. 
View parameter: page number

# Update operation (edit.xhtml)

This view shows an existing Customer for edit and stores the changes back
to database on submit. 
View parameter: customer id

# Delete operation (remove.xhtml)

This view asks the user to confirm removal of a Customer.
View parameter: customer id
