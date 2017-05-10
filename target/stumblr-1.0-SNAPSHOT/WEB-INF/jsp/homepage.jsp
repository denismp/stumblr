<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- first field for selecting what kind of post to enter -->
<div class="post-container">
    <div class="post-author">
        <img src="<c:url value="/images/lightbulb.png"/>" height="64" width="64" alt="Placeholder Posting Author Avatar"/>
    </div>
    <div class="post-content">
        <a href="<c:url value="/new/text.blr"/>">Text</a> |
        <a href="<c:url value="/new/quote.blr"/>">Quote</a> |
        <a href="<c:url value="/new/link.blr"/>">Link</a>
    </div>
</div>

<!-- loop over the posts and print them out -->
<c:forEach items="${postingList}" var="posting">
<div class="post-container">
    <div class="post-author">
        <img src="<c:url value="/images/user_message.png"/>" height="64" width="64" alt="Posting Author"/>
    </div>
    <div class="post-author">
        <c:if test="${empty post.author}">Anonymous</c:if>
        <c:if test="${not empty post.author}">${post.author}</c:if>
        :
    </div>
    <!-- check for a test post -->
    <c:if test="${posting.class.name eq 'com.rnsolutions.stumblr.entity.TextPost'}">
        <!-- see if we have a title -->
        <c:if test="${not empty posting.title}">
            <div class="post-text-title">
                ${posting.title}
            </div>
        </c:if>
        <!-- print the content -->
        <div class="post-text-content">
            ${posting.text}
        </div>
    </c:if>
    <!-- check for a quote post -->
    <c:if test="${posting.class.name eq 'com.rnsolutions.stumblr.entity.QuotePost'}">
        <!-- print the content -->
        <div class="post-quote-quote">
            "${posting.quote}"
        </div>
        <!-- see if we have a source -->
        <c:if test="${not empty posting.source}">
            <div class="post-quote-source">
                ${posting.source}
            </div>
        </c:if>
    </c:if>
    <!-- check for a link post -->
    <c:if test="${posting.class.name eq 'com.rnsolutions.stumblr.entity.LinkPost'}">
        <!-- see if we have a title -->
        <c:if test="${not empty posting.title}">
            <div class="post-link-title">
                ${posting.title}
            </div>
        </c:if>
        <!-- print the content -->
        <div class="post-link-link">
            <a href="${posting.link}">${posting.link}</a>
        </div>
    </c:if>
    <hr>
</div>
</c:forEach>