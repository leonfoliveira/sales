CREATE TABLE tb_user (
    id UUID NOT NULL,
    login VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    permission_level SMALLINT NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE tb_product (
    id UUID NOT NULL,
    title VARCHAR(200) NOT NULL,
    bar_code VARCHAR(200) NOT NULL UNIQUE,
    unit_price REAL NOT NULL,
    unit_type SMALLINT NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE tb_sale (
    id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE tb_sale_item (
    id SERIAL NOT NULL,
    product_id UUID NOT NULL,
    amount REAL NOT NULL,
    unit_price REAL NOT NULL,
    sale_id UUID NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id),
    FOREIGN KEY (sale_id) REFERENCES tb_sale(id)
);
