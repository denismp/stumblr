<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="POST" action="<c:url value="/new/text.blr"/>">
    <div>
        Title (Optional)<br/>
        <input type="text" name="title" value="" size="100" />
    </div>
    <div>
        Post<br/>
        <textarea name="postText" rows="10" cols="77"></textarea>
    </div>
    <div>
        <input type="submit" value="Create Post" />
    </div>
</form>