User authentication and authorization with custom Server Authentication Module (on Wildfly)

For testing the index page shows 2 links:

```xml
<a href="secure/resource.xhtml">Access secured resources</a>
<br/>
<a href="secure/resource.xhtml?username=angela&amp;password=peterson">Access secured resources as Angela</a>
<br/>
<a href="secure/resource.xhtm?username=logout">Logout</a>
```

Access to `secure/resource.xhtml` is limitted to authenticated user with `admin` role. Do the test as follows:
- Click on the first link (Access secured resources) and is rejected.
- Click on the second link (Access secured resources as Angela) to login as that user
- Click again on the first link and one should see it content
- Click on the last link (Logout) to log out.
- Click on the first link again and one should be rejected.