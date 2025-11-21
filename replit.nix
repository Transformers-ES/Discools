{ pkgs }: {
    deps = [
      pkgs.openssh
      pkgs.python39Packages.clvm-tools
        pkgs.graalvm17-ce
        pkgs.maven
        pkgs.replitPackages.jdt-language-server
        pkgs.replitPackages.java-debug
    ];
}