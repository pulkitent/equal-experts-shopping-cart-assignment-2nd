# Problem statement

Create a shopping cart ***package*** that facilitates 2 basic capabilities.

1. Add a product to the cart
   1. Specifying the product name and quantity
   2. Use the product name to discover the price from the Product API specified below
   3. Cart state (totals, etc.) must be available

2. Calculate the state:
   1. Cart subtotal (sum of price for all items)
   2. Tax payable (charged at 12.5% on the subtotal)
   3. Total payable (subtotal + tax)
   4. Totals should be rounded up where required


Base URL: `https://equalexperts.github.io/`

View Product: `GET /backend-take-home-test-data/{product}.json`

Available products
* `cheerios`
* `cornflakes`
* `frosties`
* `shreddies`
* `weetabix`


Example
The below is a sample with the correct values you can use to confirm your calculations

Inputs
* Add 1 × cornflakes @ 2.52 each
* Add another 1 x cornflakes @2.52 each
* Add 1 × weetabix @ 9.98 each

Results
* Cart contains 2 x cornflakes
* Cart contains 1 x weetabix
* Subtotal = 15.02
* Tax = 1.88
* Total = 16.90

# Philosophy
I am believer of evolutionary design, in which things gets improved iteration by iteration on refactoring


# Things I have tried to cover
* I have tried to add all the required domain entities/models as per the problem statement.
* I didn't make interfaces as per YAGNI principles because for now, I don't feel the need for the same. Yes, I am aware of this principle also - "Program to interfaces rather than concrete implementation".
* I have avoided breaking encapsulation by putting all the related methods & states together.
* I have created immutable states with value objects wherever possible (also avoided setters).
* I have added readable methods & variables naming to clear the intention (4 rules of simple design).
* I have used BigDecimal to avoid precision issue while doing financial calculation.
* I have added independent unit tests in "Given When Then" format with 100% coverage & with mocking wherever required & possible.
* I have used dependency injection as much as possible.


# Things I could have covered if given more time
* I could have added an integration level test by mocking external API call.
* I could have done better exception handling if product API calls gives non 200 response. Personally I don't like throwing exceptions. I generally follow Martins Fowler's Notification pattern but for nwo it's an overkill.
* I could have done better validation of input data & follow Martin Fowler's notification pattern.
* I could have cached the response of product API but for now it feels like an overkill.


# Ambiguity
* One could say ShoppingCart.java should haven't the capability to calculate total payable. I will not disagree with that for now but it's subjective. Hence, we can refactor it later.
* Integrating REST client to fetch product price for product's API.
* One could have also said that I could have taken a Map of product with quantity, but I feel having a domain model instead of primitive data structure is better.
* Mocking in unit tests. So there are 2 school of thoughts over that & Martin Fowler suggest to not do mocking in unit tests of model  