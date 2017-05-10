<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="POST" action="<c:url value="/new/quote.blr"/>">

    <div>
        Quote<br/>
        <textarea name="quote" rows="10" cols="100"></textarea>
    </div>
    <div>
        Source (Optional)<br/>
        <textarea name="source" rows="10" cols="100"></textarea>
    </div>
    <div>
        <input type="submit" value="Create Post" />
    </div>
</form>