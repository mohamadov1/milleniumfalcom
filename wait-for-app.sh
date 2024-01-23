#!/bin/sh
# wait-for-app.sh

set -e

host="$1"
shift
cmd="$@"

until wget -O - "$host" >/dev/null 2>&1; do
  >&2 echo "L'application n'est pas encore disponible - en attente"
  sleep 1
done

>&2 echo "L'application est prête - exécution de la commande"
exec $cmd