			</div> <!-- class="content" -->
			<div class="footer"><spring:message code="label.footer"/></div>
		</div> <!-- class="wrapper" -->
	</div> <!-- class="page" -->
<div class="login-form content-c">
  <div class="results-block"><span class="info"></span></div>
   <form id="loginform" action="j_spring_security_check" method="post">
    <fieldset>
      <div class="row">
      <label for="login"><spring:message code="login"/></label>
        <input class="required" type="input" name="j_username" value="" id="login"/>
    </div>
    <div class="row">
      <label for='password'><spring:message code="password"/></label>
      <div class="date-text">
        <input class="required" type="password" name="j_password" id="password"/>
      </div>
    </div>
    <div class="row">
      <div class="submit-row">
        <input title="<spring:message code="auth"/>" type="submit" class="submit" value="<spring:message code="auth"/>" />
        <div class="ml"></div>
        <input type="button" value="<spring:message code="cancel"/>" class="login close" title="<spring:message code="cancel"/>">
      </div>
    </div>
    </fieldset>
  </form>
</div>
<div class="remove-dialog content-c">
  <div class="results-block"><span class="info"></span></div>
    <fieldset>
        <div class="row">
            <p class="text" style="padding-top: 5px;"></p>
            <p class="question"></p>
        </div>
        <div class="row">
          <div class="submit-row">
            <a id="remove" class="exit" href="" title="Ð£Ð´Ð°Ð»Ð¸ÑÑ">Ð£Ð´Ð°Ð»Ð¸ÑÑ</a>
            <div class="ml"></div>
            <input type="button" value="ÐÑÐ¼ÐµÐ½Ð¸ÑÑ" class="login close" title="ÐÑÐ¼ÐµÐ½Ð¸ÑÑ">
          </div>
        </div>
    </fieldset>
</div>
</body>
</html>
