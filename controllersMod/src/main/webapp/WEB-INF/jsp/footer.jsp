		</div>
	</div>
	<div id="footer">
		<div class="container">
        	<p class="text-muted"><spring:message code="label.footer"/></p>
        </div>
    </div>
<div class="login-form content-c">
  <div class="results-block"><span class="info"></span></div>
   <form id="loginform" role="form" action="j_spring_security_check" method="post">
    
    <div class="form-group">
      <label for="login"><spring:message code="login"/></label>
      <input class="required form-control" type="input" name="j_username" value="" id="login" placeholder="Логин"/>
    </div>
    <div class="form-group">
      <label for="password"><spring:message code="password"/></label>
      <input class="required form-control" type="password" name="j_password" id="password" placeholder="Пароль"/>
    </div>
    <input class="btn btn-default" type="submit" title="<spring:message code="auth"/>"  value="<spring:message code="auth"/>" />
    <input class="btn btn-default my-close" type="button" value="<spring:message code="cancel"/>"  title="<spring:message code="cancel"/>">
  </form>
</div>
<div class="remove-dialog content-c">
  <div class="results-block"><span class="info"></span></div>
    <fieldset>
        <div class="my-row">
            <p class="text" style="padding-top: 5px;"></p>
            <p class="question"></p>
        </div>
        <div class="my-row">
          <div class="submit-my-row">
            <a id="remove" class="btn btn-default" href="" role="button" title="<spring:message code="delete"/>"><spring:message code="delete"/></a>
            <input class="btn btn-default my-close" type="button" value="<spring:message code="close"/>"  title="<spring:message code="close"/>">
          </div>
        </div>
    </fieldset>
</div>

</body>
</html>
