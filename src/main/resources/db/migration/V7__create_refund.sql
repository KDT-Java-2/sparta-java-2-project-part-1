CREATE TABLE refund (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_item_id BIGINT NOT NULL comment '구매상품 ID',
    reason varchar(255) comment '환불 사유',
    status varchar(20) comment '환불 상태',
		requested_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);