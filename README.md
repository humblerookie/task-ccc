This is a sample ecommerce app.
# Assumptions
- The app doesnt have a login since the same needs to be validated at the server.
- The Data as such is mocked in a json file in assets
- The final checkout is an implicit COD and a email is sent confirming the same.
- The presenters have been injected with data from the main application model with an appropriate provider. This can be later mocked with api requests if need be.
- A simple email using smtp provider as is being sent for confirmation using gmail with the sender address as asterrisk3@gmail.com
