SELECT 
    o.order_id, 
    o.order_date, 
    o.total_amount, 
    c.customer_name, 
    c.email, 
    oi.order_item_id, 
    oi.product_id, 
    p.product_name, 
    oi.quantity, 
    oi.price
FROM 
    orders o
JOIN 
    customers c ON o.customer_id = c.customer_id
JOIN 
    order_items oi ON o.order_id = oi.order_id
JOIN 
    products p ON oi.product_id = p.product_id
ORDER BY 
    o.order_date DESC;