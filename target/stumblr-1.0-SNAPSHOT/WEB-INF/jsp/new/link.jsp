<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="POST" action="<c:url value="/new/link.blr"/>">
    <div>
        Title (Optional)<br/>
        <input type="text" name="title" value="" size="100" />
    </div>
    <div>
        URL<br/>
        <input type="text" name="link" value="" size="100"/>
    </div>
    <div>
        <input type="submit" value="Create Post" />
    </div>
</form>