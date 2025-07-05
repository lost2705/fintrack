INSERT INTO category(name, created_at)
VALUES ('Food', CURRENT_TIMESTAMP), ('Transport', CURRENT_TIMESTAMP);

INSERT INTO expense(amount, description, timestamp, category_id)
VALUES
  (120.50, 'Groceries', CURRENT_TIMESTAMP, 1),
  (60.00, 'Metro card', CURRENT_TIMESTAMP, 2);