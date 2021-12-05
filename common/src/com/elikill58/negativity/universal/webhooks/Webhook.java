package com.elikill58.negativity.universal.webhooks;

import com.elikill58.negativity.universal.webhooks.messages.WebhookMessage;

public interface Webhook {

	/**
	 * Close webhook
	 */
	void close();
	
	/**
	 * Get the webhook name
	 * 
	 * @return webhook name
	 */
	String getWebhookName();

	/**
	 * Add message to queue of given webhook
	 * 
	 * @param msg the message to send
	 * @return true if the message is well sent
	 */
	void addToQueue(WebhookMessage msg);
	
	/**
	 * Run queue each seconds.
	 */
	void runQueue();

	/**
	 * Send the given message with adapted style to own webhook
	 * 
	 * @param msg the message to send
	 */
	void send(WebhookMessage msg);
	
	/**
	 * Send test message to webhook
	 * 
	 * @param asker Who ask for webhook ping
	 * @return true if the message is well sent
	 */
	boolean ping(String asker);
}
