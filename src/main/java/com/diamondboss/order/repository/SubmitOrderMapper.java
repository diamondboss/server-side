package com.diamondboss.order.repository;

import java.util.List;
import java.util.Map;

public interface SubmitOrderMapper {

	List<String> queryOrderByUser(Map<String, String> map);
}
