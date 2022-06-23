# Sales Management Requirements

This system should store information about products available in a regular store and about sales involving these products. This system does not store stock information.

## Entities

### User

#### Fields:

- _id_: a unique identifier.
- _login_: a unique alphanumeric text at most 20 characters long.
- _password_: a text with a minimum of 8 characters.
- _level_: see [bellow](#About-Roles).

#### About Roles

Users of the system can have one of the following permission levels:

- [0] COLLABORATOR
- [1] MANAGER
- [2] ADMIN

Note that as levels, a Manager can do everything a Collaborator can do, just like an Admin can do everything the other two can do.

### Product

#### Fields:

- _id_: a unique identifier.
- _title_: an uppercase text at most 200 characters long.
- _bar code_: a unique text with just digits.
- _unit price_: a decimal number greater than or equal 0.
- _unit type_: see [bellow](#About-Unit-Types).

#### About Unit Types

A product price can refers to one of the two measures

- [0] UNIT: the product price refers to one unit.
- [1] KG: the product price refers to one kilograms.

### Sale

- _id_: a unique identifier.
- _creation time_: date and time the sale was created
- _items_: see [bellow](#About-Sale-Items).

#### About Sale Items

A sale item must have the following informations:

- _product_: the product that was sold.
- _amount_: a positive number representing the amount of the product that was sold. Note that if the product is measured by units, than this field must be an integer, but if it's measured in kilograms, than it can be decimal.
- _unit type_: a decimal number representing the unit price of the product at the time of the sale.

The "unitType" information is crucial as the price of the products can change in the future and we don't want the total sale value to change with it.

## Stories

### About Regular Users

- As a **User**, I should be able to login in the system for the first time providing my login, so I can set up my password.
- As a **User**, I should be able to login in the system providing my login and password, so I can start to work.

### About Admins

- As an **Admin**, I should be able to create new users, so they can login and set their password on first login.
- As an **Admin**, I should be able to edit existing users, so I can fix incorrect data.
- As an **Admin**, I should be able to reset users password and get the path to set it again, so they can change it.
- As an **Admin**, I should be able to list active users.
- As an **Admin**, I should be able to toggle users activeness, so I can change their login permission.
- As an **Admin**, I should be able to delete a sale, so they can't be considered anymore.

### About Managers

- As a **Manager**, I should be able to create new products, so they can be sold.
- As a **Manager**, I should be able to edit products, so I can fix incorrect data.
- As a **Manager**, I should be able to toggle products activeness, so I can change their sell permission.
- As a **Manager**, I should be able to list all products and filter them by activeness, so I can see what is stored.
- As a **Manager**, I should be able to list all sales and filter them by a window of time, as well as see the total value of these sales, so I can see the company's earnings.

### About Collaborators

- As a **Collaborator**, I should be able to create new sales, so they can be stored.
- As a **Collaborator**, I should be able to list active products, so I can add them to a sale creation.
