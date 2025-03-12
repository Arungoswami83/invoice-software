package com.amstech.invoice.service.response;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import com.amstech.student.util.DateHelper;

public class ResponseMessage implements Map<String, Object> {

	public static final String KEY_STATUS = "status";

	public static final String KEY_STATUS_CODE = "statusCode";

	public static final String KEY_STATUS_MESSAGE = "status";

	public static final String KEY_PARAMETERS = "parameters";

	public static final String KEY_MESSAGE = "message";

	public static final String KEY_DATA = "data";

	public static final String KEY_TOTAL_RECORDS = "totalRecords";

	public static final String KEY_TOTAL_UN_READ = "unReadCount";

	public static final String KEY_TOTAL_PAGES = "totalPages";

	public static final String KEY_PAGE_SIZE = "pageSize";

	public static final String KEY_PAGE_NUMBER = "pageNumber";

	public static final String KEY_ROW_COUNT = "rowCount";

	public static final String KEY_CURRENT_TIMESTAMP = "currentTimestamp";

	public static final String STATUS_ERROR = "error";

	public static final String STATUS_SUCCESS = "success";

	private Map<String, Object> response = new LinkedHashMap<>();

	private ResponseMessage() {
	}

	public static ResponseMessage build() {
		return new ResponseMessage().withCurrentTimestamp(DateHelper.getCurrentDate().getTime());
	}
	public ResponseMessage add(String name, Object value) {
		this.response.put(name, value);
		return this;
	}

	public ResponseMessage withPageNumber(long value) {
		return add(KEY_PAGE_NUMBER, value);
	}

	public ResponseMessage withTotalRecords(long value) {
		return add(KEY_TOTAL_RECORDS, value);
	}

	public ResponseMessage withPageSize(long value) {
		return add(KEY_PAGE_SIZE, value);
	}

	public ResponseMessage withCurrentTimestamp(long value) {
		return add(KEY_CURRENT_TIMESTAMP, value);
	}

	public ResponseMessage withMessage(String message) {
		return add(KEY_MESSAGE, message);
	}

	public ResponseMessage withStatusCode(Object statusCode) {
		return add(KEY_STATUS_CODE, statusCode);
	}

	public ResponseMessage withError(String error) {
		return add(KEY_STATUS, STATUS_ERROR).add(KEY_MESSAGE, error);
	}

	public ResponseMessage withError(String errorMessage, Object statusCode, Object statusMessage, String[] parameters) {
		return add(KEY_STATUS, STATUS_ERROR).add(KEY_MESSAGE, errorMessage).add(KEY_STATUS_CODE, statusCode).add(KEY_STATUS_MESSAGE, statusMessage).add(KEY_PARAMETERS, parameters);
	}

	public ResponseMessage withSuccess(String message) {
		return add(KEY_STATUS, STATUS_SUCCESS).add(KEY_MESSAGE, message);
	}

	public ResponseMessage withSuccess() {
		return add(KEY_STATUS, STATUS_SUCCESS);
	}

	public ResponseMessage withSuccess(String message, Object data) {
		return withSuccess(message, KEY_DATA, data);
	}

	public ResponseMessage withSuccess(String message, Object data, Object statusCode, Object statusMessage) {
		return withSuccess(message, KEY_DATA, data).add(KEY_STATUS_CODE, statusCode).add(KEY_STATUS_MESSAGE, statusMessage);
	}

	public ResponseMessage withSuccess(String message, String name, Object value) {
		return withSuccess(message).add(name, value);
	}

	public ResponseMessage withData(Object data) {
		return add(KEY_DATA, data);
	}

	public ResponseMessage withData(Long totalPages, Long totalSize, Integer pageNumber, String key, Object value) {
		if (!response.containsKey(KEY_DATA)) {
			add(KEY_TOTAL_RECORDS, totalSize).add(KEY_TOTAL_PAGES, totalPages).add(KEY_PAGE_NUMBER, pageNumber)
					// .add(KEY_DATA, new LinkedHashMap<String, Object>());
					.add(KEY_DATA, value);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public ResponseMessage withData(String key, Object value) {
		if (!response.containsKey(KEY_DATA)) {
			add(KEY_DATA, new LinkedHashMap<String, Object>());
		}
		((Map<String, Object>) response.get(KEY_DATA)).put(key, value);
		return this;
	}

	public ResponseMessage withUnReadCount(long count) {
		return add(KEY_TOTAL_UN_READ, count);
	}

	public static ResponseMessage success() {
		return new ResponseMessage().withSuccess();
	}

	public static ResponseMessage success(String message) {
		return new ResponseMessage().withSuccess(message);
	}

	public static ResponseMessage success(String message, Object data) {
		return ResponseMessage.success(message, KEY_DATA, data);
	}

	public static ResponseMessage success2(String message, Object data) {
		throw new IllegalStateException();
	}

	public static ResponseMessage success2() {
		throw new IllegalStateException();
	}

	public static ResponseMessage success(String message, String name, Object data) {
		return new ResponseMessage().withSuccess(message).add(name, data);
	}

	public static ResponseMessage error(String errorMessage) {
		return new ResponseMessage().withError(errorMessage);
	}

	public static ResponseMessage error(Exception e) {
		return new ResponseMessage().withError(e.getMessage());
	}

	public static ResponseMessage missingParam(String param) {
		return new ResponseMessage().withError("Param " + param + " is missing");
	}

	@Override
	public int size() {
		return response.size();
	}

	@Override
	public boolean isEmpty() {
		return response.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return response.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return response.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return response.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return response.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return response.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		response.putAll(m);
	}

	@Override
	public void clear() {
		response.clear();

	}

	@Override
	public Set<String> keySet() {
		return response.keySet();
	}

	@Override
	public Collection<Object> values() {

		return response.values();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return response.entrySet();
	}

}
