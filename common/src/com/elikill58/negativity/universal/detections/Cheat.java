package com.elikill58.negativity.universal.detections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.elikill58.negativity.api.NegativityPlayer;
import com.elikill58.negativity.api.entity.Player;
import com.elikill58.negativity.api.events.EventManager;
import com.elikill58.negativity.api.events.Listeners;
import com.elikill58.negativity.api.item.Material;
import com.elikill58.negativity.api.json.JSONObject;
import com.elikill58.negativity.api.json.parser.JSONParser;
import com.elikill58.negativity.common.protocols.CheckManager;
import com.elikill58.negativity.universal.Adapter;
import com.elikill58.negativity.universal.TranslatedMessages;
import com.elikill58.negativity.universal.detections.keys.CheatKeys;
import com.elikill58.negativity.universal.setBack.SetBackEntry;
import com.elikill58.negativity.universal.setBack.SetBackProcessor;
import com.elikill58.negativity.universal.setBack.processor.PotionEffectProcessor;
import com.elikill58.negativity.universal.setBack.processor.TeleportProcessor;
import com.elikill58.negativity.universal.setBack.processor.ValueEditorProcessor;
import com.elikill58.negativity.universal.verif.VerifData;
import com.elikill58.negativity.universal.verif.VerificationManager;

public abstract class Cheat extends AbstractDetection<CheatKeys> {

	public static final List<Cheat> CHEATS = new ArrayList<>();
	private static CheckManager checkManager;
	public static CheckManager getCheckManager() {
		return checkManager;
	}
	private boolean hasVerif;
	private CheatCategory cheatCategory;
	private final List<SetBackProcessor> setBackProcessor = new ArrayList<>();

	/**
	 * Create a new cheat object and load default config
	 * 
	 * @param key the cheat key
	 * @param type the cheat category
	 * @param m the material used in inventory to represent this cheat
	 * @param needPacket if the cheat need packet in detections
	 * @param hasVerif know if the cheat can be used in verification system
	 * @param alias all other names of the cheat
	 */
	public Cheat(CheatKeys key, CheatCategory type, Material m, boolean needPacket, boolean hasVerif, String... alias) {
		super(key, m, needPacket, alias);
		this.cheatCategory = type;
		this.hasVerif = hasVerif;
		
		this.config.getStringList("set_back.action").forEach((line) -> {
			JSONObject json = null;
			try {
				json = (JSONObject) new JSONParser().parse(line);
			} catch (Exception e) {}
			SetBackEntry entry = json == null ? new SetBackEntry(line) : new SetBackEntry(json);
			switch (entry.getType().toLowerCase(Locale.ROOT)) {
			case "potion_effect":
				setBackProcessor.add(new PotionEffectProcessor(entry));
				break;
			case "teleport":
				setBackProcessor.add(new TeleportProcessor(entry));
				break;
			case "value_editor":
				setBackProcessor.add(new ValueEditorProcessor(entry));
				break;
			default:
				break;
			}
		});
	}
	
	/**
	 * Get the exact name of the cheat
	 * 
	 * @return the name
	 */
	public String getName() {
		return config.getString("exact_name", key.getLowerKey());
	}
	
	/**
	 * Check if a detection is active
	 * 
	 * @param checkName the name of the detection
	 * @return true if the detection is active
	 */
	public boolean checkActive(String checkName) {
		return config.getBoolean("checks." + checkName + ".active", true);
	}
	
	/**
	 * Check if the cheat is blocked in fight.
	 * Overrided if true, by default it's false
	 * 
	 * @return true if the cheat is blocked in fight
	 */
	public boolean isBlockedInFight() {
		return false;
	}
	
	/**
	 * Get the category of the cheat
	 * 
	 * @return the cheat category
	 */
	public CheatCategory getCheatCategory() {
		return cheatCategory;
	}

	/**
	 * Get needed reliability to see alert
	 * By default it's 60%
	 * 
	 * @return the needed reliability
	 */
	public int getReliabilityAlert() {
		return config.getInt("reliability_alert", 60);
	}

	/**
	 * Check if the setBack option is enabled
	 * 
	 * @return true if enabled
	 */
	public boolean isSetBack() {
		return config.getBoolean("set_back.active", false);
	}

	/**
	 * Check if the cheat is allowed to kick player
	 * 
	 * @return true if kick is allowed
	 */
	public boolean allowKick() {
		return config.getBoolean("kick.active", false);
	}

	/**
	 * Set the allowability to kick
	 * Warn: this don't save the config
	 * 
	 * @param b the new value
	 * @return the given boolean value
	 */
	public boolean setAllowKick(boolean b) {
		config.set("kick.active", b);
		return b;
	}

	/**
	 * Get the amount of needed alert to kick
	 * By default it's 5
	 * 
	 * @return the needed alert counter
	 */
	public int getAlertToKick() {
		return config.getInt("kick.alert", 5);
	}


	/**
	 * Set if setback option is active
	 * Warn: this don't save the config
	 * 
	 * @param b the new value
	 * @return the given boolean value
	 */
	public boolean setBack(boolean b) {
		config.set("set_back.active", b);
		return b;
	}

	/**
	 * Get the maximum ping to create alert
	 * By default it's 150ms
	 * 
	 * @return the max ping to create alert
	 */
	public int getMaxAlertPing() {
		return config.getInt("ping", 150);
	}

	/**
	 * Set if the cheat is used in verif
	 * Warn: this don't save the config
	 * 
	 * @param verif the new value
	 */
	public void setVerif(boolean verif) {
		config.set("verif.check_in_verif", verif);
	}

	/**
	 * Check if the cheat can verif someone
	 * 
	 * @return true if the cheat can verif
	 */
	public boolean hasVerif() {
		return hasVerif && config.getBoolean("verif.check_in_verif", true);
	}
	
	/**
	 * Use set back option of the player
	 * Warn: this doesn't check if setBack option is enabled
	 * 
	 * @param p the player which have to be set back
	 */
	public void performSetBack(Player p) {
		setBackProcessor.forEach((st) -> st.perform(p));
	}

	/**
	 * Create a new hover message
	 * 
	 * @param key the key of the message
	 * @param placeholders all placeholders of the message
	 * @return the cheatHover which  will be showed
	 */
	public CheatHover hoverMsg(String key, Object... placeholders) {
		return new CheatHover("hover." + this.key + "." + key, placeholders);
	}
	
	/**
	 * Create a verification summary
	 * 
	 * @param data the verif data
	 * @param np the negativity player
	 * @return the summary (or null if cheat didn't verif/find something)
	 */
	public @Nullable String makeVerificationSummary(VerifData data, NegativityPlayer np) { return null; }
	
	/**
	 * Add data to verification
	 * 
	 * @param target the player which create the data
	 * @param type the data type
	 * @param value the value recorded
	 */
	public final <T> void recordData(UUID target, VerifData.DataType<T> type, T value) {
		VerificationManager.recordData(target, this, type, value);
	}
	
	/**
	 * Get cheat from a name
	 * Can be the key, the name or one of the alias
	 * 
	 * @param name the cheat name
	 * @return the cheat or null if anything found
	 */
	public static Cheat fromString(String name) {
		for (Cheat c : Cheat.values()) {
			try {
				if (c.getKey().getKey().equalsIgnoreCase(name) || c.getName().equalsIgnoreCase(name) || Arrays.asList(c.getAliases()).contains(name))
					return c;
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Get the cheat according to the key
	 * 
	 * @param key the cheat key
	 * @return the cheat or null if anything is found
	 */
	public static Cheat forKey(CheatKeys key) {
		return CHEATS.stream().filter((c) -> c.getKey().equals(key)).findAny().orElse(null);
	}
	
	/**
	 * Get all cheat keys
	 * 
	 * @return all cheat keys
	 */
	public static Set<CheatKeys> getCheatKeys(){
		return Cheat.CHEATS.stream().collect(Collectors.groupingBy(Cheat::getKey)).keySet();
	}
	
	/**
	 * Return a map of cheat keys and their corresponding cheat instance
	 * 
	 * @return keys and their cheat
	 */
	public static Map<CheatKeys, Cheat> getCheatByKeys(){
		return CHEATS.stream().collect(Collectors.toMap(Cheat::getKey, Function.identity()));
	}
	
	/**
	 * Load all cheat
	 * Support reload
	 */
	public static void loadCheat() {
		CHEATS.clear();
		Adapter ada = Adapter.getAdapter();
		int futurCheat = 0;
		for (Cheat cheat : ServiceLoader.load(Cheat.class, Cheat.class.getClassLoader())) {
			if(cheat instanceof Listeners) {
				try {
					EventManager.registerEvent((Listeners) cheat);
				} catch (Exception e) {
					ada.getLogger().error("Failed to register cheat " + cheat.getName() + " as a listener");
					e.printStackTrace();
				}
			}
			if(cheat.getKey().getMinVersion().isNewerThan(ada.getServerVersion())) {// cheat made for futur version - no need to show it
				futurCheat++;
				continue;
			}
			CHEATS.add(cheat);
		}
		CHEATS.sort(Comparator.comparing(Cheat::getKey));
		
		EventManager.unregisterEventForClass(CheckManager.class);
		EventManager.registerEvent(checkManager = new CheckManager(CHEATS));
		ada.getLogger().info("Loaded " + CHEATS.size() + " cheat detections" + (futurCheat > 0 ? " (" + futurCheat + " disabled because of require newer MC version)." : "."));
	}

	public static List<Cheat> values() {
		return CHEATS;
	}
	
	public static List<Cheat> getEnabledCheat() {
		return new ArrayList<>(CHEATS).stream().filter(Cheat::isActive).collect(Collectors.toList());
	}

	public enum CheatCategory {
		
		/**
		 * Edit combat value such as attack speed of attack distance
		 */
		COMBAT,
		
		/**
		 * Edit player movement
		 */
		MOVEMENT,
		/**
		 * Edit the world of the player such as block breaker
		 */
		WORLD,
		/**
		 * Edit player variable/abilities directly
		 */
		PLAYER
	}
	
	public static class CheatHover {
		private final String key;
		private final Object[] placeholders;
		
		/**
		 * Create a new cheat hover
		 * 
		 * @param key the key of the message which will be translated
		 * @param placeholders the placeholders of the message
		 */
		public CheatHover(String key, Object... placeholders) {
			this.key = Objects.requireNonNull(key);
			this.placeholders = placeholders;
		}

		/**
		 * Get the message key
		 * 
		 * @return the message key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * Get all message placeholders
		 * 
		 * @return placeholders
		 */
		public Object[] getPlaceholders() {
			return placeholders;
		}
		
		/**
		 * Compile message with default lang
		 * Prefer use {@link #compile(NegativityPlayer)} to have a message translated for each player
		 * 
		 * @return the translated message
		 */
		public String compile() {
			return TranslatedMessages.getStringFromLang(TranslatedMessages.DEFAULT_LANG, getKey(), getPlaceholders());
		}
		
		/**
		 * Compile message with the lang of the given negativity player
		 * 
		 * @param np the player which will receive the message
		 * @return the translated message
		 */
		public String compile(NegativityPlayer np) {
			return TranslatedMessages.getStringFromLang(np.getAccount().getLang(), getKey(), getPlaceholders());
		}

		/**
		 * This class is used to have CheatHover but not translated
		 * It's useful when you have a cheat hover to show but don't want to edit lang message
		 * 
		 */
		public static class Literal extends CheatHover {
			public Literal(String text) {
				super(text);
			}

			@Override
			public String compile() {
				return getKey();
			}

			@Override
			public String compile(NegativityPlayer np) {
				return getKey();
			}
		}
	}
}