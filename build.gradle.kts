plugins {
    id("dev.tocraft.modmaster.version")
}

ext {
    val modMeta = mutableMapOf<String, Any>()
    modMeta["minecraft_version"] = project.name
    modMeta["version"] = version
    set("mod_meta", modMeta)
}