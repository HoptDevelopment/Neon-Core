package com.roguehcf.neon.security.command;

public class EmailCommand {

	/*
	 * TODO
	 * 
	 * This is a command where staff can optionally set an email to get an automated confirmation message sent to them when they join on a new IP address instead
	 * of having to go into TeamSpeak to confirm their identity.
	 * 
	 * (cool little feature ay, pretty sure this is easily done too if we use mongo or mysql.)
	 * https://stackoverflow.com/questions/32086740/how-you-create-confirmation-link-for-email
	 * https://github.com/AssistDev/EmailRegister-WIP/tree/master/src/main/java/me/avastprods/emailregister
	 * https://stackoverflow.com/questions/15597616/sending-email-via-gmail-smtp-server-in-java
	 * https://dev.bukkit.org/projects/emailregister
	 * 
	 * Another easy way to do this is they do /email [email] , it sends an email to their email address with the confirmation code, they do /email confirm [code]
	 * and if the code matches the code linked to them in a hashmap or config file, it confirms it.
	 * 
	 * Then for the "This is a command where staff can optionally set an email to get an automated confirmation message sent to them when they join on a new IP address instead
	 * of having to go into TeamSpeak to confirm their identity." send them a different code (UUID.randomUUID().toString()) and make them /login confirm [code].
	 * 
	 * This is just a bit overkill but makes the server hella secure and could up the price quite a lot if we decide to sell it on mc-market.
	 * 
	 */
}
