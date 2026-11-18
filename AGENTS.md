# Repository agent policy

## Owner directive: additive best-of-best merges, full history and last checked (2026-11-18)

Before replacing a class/method/algorithm, traverse all accessible commit history back to its root(s), every branch and tag, and all paginated PRs (open, closed, merged, rejected) with PR source/merge refs. Compare actual before/after owner source blobs, member inventories, contracts, tests and resource guarantees; merge ancestry alone is not evidence that earlier code survived. Detect bad commits and restore lost verified algorithms/APIs/tests additively using the canonical Synexia recipe, without deleting/rebasing history.

Choose strongest compatible implementations and combine them into an additive superset rather than winner-takes-all code replacement. Preserve public/protected source and binary API, reflection, serialization, null/exception semantics, concurrency, performance and memory properties. The canonical recipe, atomization/patternization, source-sealed and loss-detection process resides in `hsoliwal/com.synexia`.

Prefer Apache-2.0 for original eligible implementations; honor all inherited license variants and exceptions, notices and provenance. Ideas and abstract algorithms can be independently implemented when lawful; an LLM rewrite does not automatically remove third-party source copyright or relevant patent obligations.

Record verified ref SHA/date, reachable roots, branch and PR enumeration, delivered-source comparison, tests and unresolved losses in the private canonical ledger `hsoliwal/com.synexia/docs/M3-SCALE/invariants/SYNEXIA-HISTORY-REVIEW-LAST-CHECKED.tsv`. Until the entire history and source/contract gates run, label the review PARTIAL.


## Superset merge and history-audit rule (2026-11-18)

Read [.github/MERGE_RULE.md](.github/MERGE_RULE.md) before changing an existing implementation or integrating any branch or PR.
Preserve the supported additive API/behavior superset and select implementations by correctness, predictability, measured performance, then elegance.
Audit all reachable history back to every root, all branches/tags and all PR states for lost code or regressions; preserve append-only history.
Record actual UTC coverage and remaining work in [.github/HISTORY_AUDIT.tsv](.github/HISTORY_AUDIT.tsv); policy installation is not a completed source-history audit.
Prefer verified Apache-2.0 sources and independent implementations while preserving all existing third-party rights; LLM rewriting does not remove license obligations.
Detailed private recipe/proof records remain in canonical Synexia; the compact local policy and checkpoint make the rule discoverable here.
<!-- MERGE-SUPERSET-HISTORY-20261118-2 -->
