This is a sample ecommerce app.
# Assumptions
- The app doesnt have a login since the same needs to be validated at the server.
- The Data as such is mocked in a json file in assets
- The final checkout is an implicit COD and a email is sent confirming the same.
- The presenters have been injected with data from the main application model with an appropriate provider. This can be later mocked with api requests if need be.
- A simple email using smtp provider as is being sent for confirmation using gmail with the sender address as asterrisk3@gmail.com. The credentials are emebeded within however this is purely for reference and shouldnt be used as such.

## Primary Features Built
 - Search
 - Category wise Store front page
 - Cart and Checkout
 - Email confirmation
 
## Secondary Features Absent
 - Category wise explore with Filter and Sort
 - Authentication
 - Payment gateway(orders are presumed COD :D )
