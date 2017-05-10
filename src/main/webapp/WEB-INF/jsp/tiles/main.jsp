<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>
            <tiles:getAsString name="title"/>
        </title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>" media="screen" />
    <tiles:insertAttribute name="htmlHead"/>
</head>

<body>

    <div id="wrap">

        <div id="top"></div>

        <div id="content">

            <tiles:insertAttribute name="header"/>

            <div class="breadcrumbs">
               <!-- <a href="#">Home</a> --> 
                <a href="/homepage.blr">Home</a>
            </div>

            <div class="middle">
                <tiles:insertAttribute name="body"/>
            </div>

            <div id="clear"></div>

        </div>

        <div id="bottom"></div>

    </div>

<tiles:insertAttribute name="footer"/>

</body>
</html>