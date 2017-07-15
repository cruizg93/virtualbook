<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value='/static/css/index.css' />" rel="stylesheet"></link>
<c:url var="loginUrl" value="/login" />

<c:if test="${param.logout != null}">
    <div class="alert alert-success">
        <p>You have been logged out successfully.</p>
    </div>
</c:if>

<div class="container content">
	<div class="login-form col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2 well-lg">
		<form action="${loginUrl}" method="post" class="form-horizontal">
		<fieldset>
			<legend class="title">Login: <span class="hidden-xs">Black Tie Rentals</span> </legend>
			<div class="input-group input-group-lg">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</span><input type="text" class="form-control" id="username" name="username"
					placeholder="Username" required>
			</div>
			<div class="input-group input-group-lg">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-lock" aria-hidden="true"></span>
				</span><input type="password" class="form-control" id="password"
					name="password" placeholder="Password" required>
			</div>
			<div class="input-group input-sm">
				<div class="checkbox">
					<label><input type="checkbox" id="rememberme"
						name="remember-me"> Remember Me</label>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="form-actions">
				<button type="submit" class="btn btn-success btn-lg col-xs-12"
					value="login">Enter</button>
				<p class="text-center">
					or<br />
					<a href="#">forgot password</a>
				</p>
			</div>
			<c:if test="${param.error != null}">
			    <div class="alert alert-danger">
			        <p>Invalid username and password.</p>
			    </div>
			</c:if>
		</fieldset>
	</form>
		
	</div>
</div>